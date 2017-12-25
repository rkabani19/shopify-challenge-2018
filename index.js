const request = require('request');
const API_ENDPOINT = 'https://backend-challenge-summer-2018.herokuapp.com/challenges.json?id=1&page=';

let page = 2;

//get API response
request({
  url: `${API_ENDPOINT}${page}`,
  json: true
}, function (error, response, body) {
  console.log(JSON.stringify(body, undefined, 1));
});
