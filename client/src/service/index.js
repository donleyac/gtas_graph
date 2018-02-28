import axios from 'axios';
import queryString from 'query-string';

const SERVER = "http://localhost:8080"
const ROOT = "/gtas-graph"
const CATEGORY_MAP = {
	"all": "/allPassengerdata",
	"Passenger": "/passengerFilter",
	"Flight": "/flightFilter",
	"Address": "/addressFilter",
	"Agency": "/agencyFilter",
	"Document": "/documentFilter"
}
export function getPassengerByCriteria(criteria, category){
	console.log(criteria, CATEGORY_MAP[category]);
	const URL = SERVER + ROOT + CATEGORY_MAP[category];
	return axios({
		method: 'post',
		url: URL,
		data: criteria
	});
}