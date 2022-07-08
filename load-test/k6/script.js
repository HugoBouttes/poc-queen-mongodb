import http from 'k6/http';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';
import { check, sleep, group } from 'k6';



export const options = {
  stages: [
    { duration: "20s", target: 10 }, // simulate ramp-up of traffic from 0 to 10 users over 20 minutes.
    { duration: "60s", target: 10 }, // stay at 10 users for 60m minutes
    { duration: "20s", target: 0 }, // ramp-down to 0 users over 20 minutes
  ],
  //vus: 10,
  //iterations: 1,
  //duration: "3600s",
  setupTimeout: "300s",
};

const nbQuestions = 100;
const iterMax = 20;

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

  const {host} = "localhost:8080";
  
  /** on génère plus mais on prend ce qu'on a généré**/ 
  

  const arrData = safeGet(
    "https://minio.lab.sspcloud.fr/hbouttes/Data.json"
  );

  const arrParadata = safeGet(
    "https://minio.lab.sspcloud.fr/hbouttes/Paradata.json"
  );


  const arrStateData = safeGet(
    "https://minio.lab.sspcloud.fr/hbouttes/StateData.json"
  ); 

  const randomSurveyUnit = randomIntBetween(0, 50000)
  return {
    idCampaign,
    arrData,
    arrParadata,
    arrStateData,
    randomSurveyUnit
  };
}

export default function (data) {
  /****Init : get model, metadata and nomenclatures****/
  group("Init questionnaire", function () {
    const { idCampaign } = data.idCampaign;
    /**const {host} = "localhost:8080";**/ 

    const res = http.get(
      `https://demoqueenmongo.dev.insee.io/api/questionnaire`
    );

    check(res, {
      "status 200 get questionnaire model": (r) => r.status === 200,
    });

    const res2 = http.get(
      `https://demoqueenmongo.dev.insee.io/api/metadata`
    );
    check(res2, {
      "status 200 get campaign metadata": (r) => r.status === 200,
    });

    const res3 = http.get(
      `https://demoqueenmongo.dev.insee.io/api/required-nomenclatures`
    );
    check(res3, {
      "status 200 get required-nomenclatures": (r) => r.status === 200,
    });

    const oui = {"nomenclatures":["COMMUNEPASSEE","DEPNAIS","NATIONETR","NOMENCLATURES_HORS_TCM","PAYSNAIS"]};
    const str = JSON.stringify(oui);
    const removedebChar = str.slice(18,str.length - 2);
    const myArray = removedebChar.split(",");
    for (const elt of myArray) {
      const elta = elt.slice(1,elt.length-1);
      const res4 = http.get(
        `https://demoqueenmongo.dev.insee.io/api/nomenclature/${elta}`
      );
      check(res4, { "status 200 get nomenclature": (r) => r.status === 200 });
    };
  });

    /****Filling out questionnaire and paradata****/
    group("Filling out questionnaire", function () {
      /**const currentId = (20 - 1) * iterMax + __ITER **/
      /** const idSurveyUnit = data.arrIdSurveyUnit[currentId]; **/
  
      function fillingOutQuestions(end, current) {
        if (current < end) {
          const idSurveyUnit = "id" + data.randomSurveyUnit;
          const iterationParadata = data.arrParadata[current];
          const iterationStateData = data.arrStateData[current];
          const iterationData = data.arrData[current];           
          const params = { headers: { "Content-type": "application/json" } };
          
          console.log(idSurveyUnit);

          var iterationData2 = "";

          for (let i = 0; i < current +1 ; i++) {
            var iterationData3 = JSON.stringify(iterationData);
            var iterationData2 =  iterationData2 + '"'+"data" + i + '" : '    + iterationData3 +"," ;
          }; 
          var iterationData2 = iterationData2.slice(0,iterationData2.length - 1 );
          var iterationData2 = "{" + iterationData2 + "}";       

          const r = 2 + randomIntBetween(0, 8);
          
          var iterationParadata2 = "";

          for (let i = 0; i < r; i++) {
          var iterationParadata3 = JSON.stringify(iterationParadata);
              iterationParadata2 =  iterationParadata2 + '"' + "paradataEvents" + i + '" : ' + iterationParadata3 + ",";
          };
          var iterationParadata2 = iterationParadata2.slice(0,iterationParadata2.length - 1 );
          var iterationParadata2 = "{" + iterationParadata2 + "}"; 
          console.log(iterationParadata2);
          const  res6 = http.post(
            `https://demoqueenmongo.dev.insee.io/api/paradata`, 
            iterationParadata2,
            params
          );
          check(res6, { "status 201 post paradata": (r) => r.status === 201 });

          
          
          
          const res5 = http.put(
            `https://demoqueenmongo.dev.insee.io/api/survey-units/${idSurveyUnit}/data`,
            iterationData2,
            params
          );
          check(res5, { "status 200 put data": (r) => r.status === 200 });
          

          const iterationStateData2 = JSON.stringify(iterationStateData);
          
          const res7 = http.put(
            `https://demoqueenmongo.dev.insee.io/api/survey-units/${idSurveyUnit}/state-data`,
            iterationStateData2,
            params
          );
          check(res7, { "status 200 put state-data": (r) => r.status === 200 });
  
  
          const randomSleep = 3 + randomIntBetween(0, 7);
          sleep(randomSleep);
  
          fillingOutQuestions(end, current + 1);
        }
      }
  
      fillingOutQuestions(92, 0);
  
    }); 


}