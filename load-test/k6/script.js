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
  const res = http.get('https://demoqueenmongo.dev.insee.io/api/paradata/');
  const res2 = http.get('https://demoqueenmongo.dev.insee.io/api/metadata/');
  check(res, { 'status was 200': (r) => r.status == 200 });
  sleep(1);
  check(res2, { 'status was 200': (r) => r.status == 200 });
}
