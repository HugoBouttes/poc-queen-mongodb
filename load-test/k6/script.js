import http from 'k6/http';
import { check, sleep } from 'k6';



export const options = {
  stages: [
    { duration: "5s", target: 20 }, // simulate ramp-up of traffic from 1 to ${__ENV.VUS} users over 20 minutes.
    { duration: "2m", target: 20 }, // stay at ${__ENV.VUS} users for 60m minutes
    { duration: "5s", target: 0 }, // ramp-down to 0 users over 20 minutes
  ],
  //vus: 1,
  //iterations: 1,
  //duration: "3600s",
  setupTimeout: "300s",
};

const nbQuestions = 70;
const iterMax = 20;
const {host} = "localhost:8080";

function safeGet(url) {
  const { status, body } = http.get(url);
  if (status != 200) {
    throw new Error(`Setup failed : GET ${url} ${status}`);
  }
  return  JSON.parse(body);
}



export function setup() {

  const idCampaign = "kwi5uegy3101";

  const length = 20 * iterMax;
  
  /** on génère plus mais on prend ce qu'on a généré**/ 
  

  const arrData = safeGet(
    "https://minio.lab.sspcloud.fr/hbouttes/Data.json"
  );

  const arrParadata = safeGet(
    "https://minio.lab.sspcloud.fr/hbouttes/Paradata.json"
  );


  const arrStateData = safeGet(
    "https://minio.lab.sspcloud.fr/hbouttes/State-Data.json"
  );

  return {
    idCampaign,
    arrData,
    arrParadata,
  };
}

export default function (data) {
  /****Init : get model, metadata and nomenclatures****/
  group("Init questionnaire", function () {
    const { idCampaign } = data.idCampaign;

    const res = http.get(
      `https://${host}/api/campaign/${idCampaign}/questionnaire`
    );

    check(res, {
      "status 200 get questionnaire model": (r) => r.status === 200,
    });

    const res2 = http.get(
      `https://${host}/api/campaign/${idCampaign}/metadata`
    );
    check(res2, {
      "status 200 get campaign metadata": (r) => r.status === 200,
    });

    const res3 = http.get(
      `https://${host}/api/campaign/${idCampaign}/required-nomenclatures`
    );
    check(res3, {
      "status 200 get required-nomenclatures": (r) => r.status === 200,
    });

    res3.json().forEach(function (elt) {
      const res4 = http.get(
        `https://${host}/api/nomenclature/${elt}`
      );
      check(res4, { "status 200 get nomenclature": (r) => r.status === 200 });
    });
  });



  /****Filling out questionnaire and paradata****/
  group("Filling out questionnaire", function () {
    const currentId = (20 - 1) * iterMax + __ITER
    const idSurveyUnit = data.arrIdSurveyUnit[currentId];
    const end = 70;

    const iterationData = [];
    function fillingOutQuestions(end, current = 0) {
      if (current < end) {
        const idSurveyUnit = current;
        const iterationData2 = data.arrData[current] ;
        const iterationParadata = data.arrParadata[current];

        /**for (var i = 0; i < current ; i++) {
          var counter = data.arrData[i];
          iterationData = iterationData + counter;
          } **/

        const iterationStateData = data.arrStateData[current];

        const iterationData = iterationData.concat(iterationData2);
        
        const r = 2 + getRandomInt(8);

        for (let i = 0; i < r; i++) {
          const res6 = http.post(
            `https://${host}/api/paradata`, /* pioche dedans et random(2,10) */
            iterationParadata,
            params
          );
          check(res6, { "status 200 post": (r) => r.status === 200 });
        }



        const params = { headers: { "Content-type": "application/json" } };

        const res5 = http.put(
          `https://${host}/api/survey-unit/${idSurveyUnit}/data`,
          iterationData,
          params
        );
        check(res5, { "status 200 put": (r) => r.status === 200 });

        const res7 = http.put(
          `https://${host}/api/survey-unit/${idSurveyUnit}/state-data`,
          iterationStateData,
          params
        );
        check(res7, { "status 200 put": (r) => r.status === 200 });


        
        sleep(3 + Math.random() * 7);

        fillingOutQuestions(end, current + 1);
      }
    }
    fillingOutQuestions(data.arrData.length);
  }); 
}