import http from 'k6/http';
import { check, sleep } from 'k6';


export const options = {
    stages: [
      { duration: "30s", target: 20 }, // simulate ramp-up of traffic from 1 to 20 users over 20 sceonds.
      { duration: "1m30s", target: 15 }, // stay at 15 users for 1 minute 30 seconds
      { duration: "20s", target: 0 }, // ramp-down to 0 users over 20 seconds 
    ],
    //vus: 1,
    //iterations: 1,
    //duration: "3600s",
    setupTimeout: "300s",
  };


  export default function () {
    const res = http.get('https://demoqueenmongo.dev.insee.io/api/paradata');
    check(res, { 'status was 200': (r) => r.status == 200 });
    sleep(1);
  }
  