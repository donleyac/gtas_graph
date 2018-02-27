import axios from 'axios';
import queryString from 'query-string';

const SERVER = "http://localhost:8080"
const ROOT = "/gtas-graph"
export function getPassengerByCriteria(criteria){
	console.log(criteria);
	const FULLPATH = SERVER + ROOT + "/searchByCriteria";
	console.log(queryString.stringify(criteria));
	return axios.get(FULLPATH, queryString.stringify(criteria));
}