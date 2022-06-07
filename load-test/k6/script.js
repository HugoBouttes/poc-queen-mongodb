import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '30s', target: 20 },
    { duration: '1m30s', target: 10 },
    { duration: '20s', target: 0 },
  ],
};

export default function () {
  const res1 = http.get('https://demoqueenmongo.dev.insee.io/api/survey-units/');
  /****required nomenclature****/
  const res3 = http.get('https://demoqueenmongo.dev.insee.io/api/nomenclature/');
  /****questionnaire modèle****/
  const res5 = http.get('https://demoqueenmongo.dev.insee.io/api/metadata/');
  /****personnalization ****/
  const res6 = http.put('https://demoqueenmongo.dev.insee.io/api/state-data/', ,params);
  const res6 = http.put('https://demoqueenmongo.dev.insee.io/api/data/', ,params);
  

  check(res1, { 'status was 200': (r) => r.status == 200 });
  sleep(1);
  check(res2, { 'status was 200': (r) => r.status == 200 });
  sleep(1);
}
