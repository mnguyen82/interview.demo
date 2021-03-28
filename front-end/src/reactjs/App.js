import React, { useMemo, useState, useEffect } from "react";
import ReactDOM from "react-dom";
import 'regenerator-runtime/runtime'
import axios from 'axios';
import Table from "./component/Table/Table";
import LoadingIndicator from "./component/LoadingIndicator/LoadingIndicator";

import "./App.css";

/**
 * Main component
 * @returns a div that includes 2 components: Table and Loading Indicator
 */
function App() {
	// data state to store MARTA train real-time departure API data. Its initial value is an empty array
	const [state, setState] = useState({
		data:[],
		loadingIndicatorCss:"showLoading"
	});

	// Using useEffect to fetch data once mounted and set the data
	useEffect(() => {
		(fetData)();
	}, []);
	
	async function fetData() {
		const result = await  axios("/getTrainRealtimeData");// Rest API url
		setState({
			data:result.data,
			loadingIndicatorCss:"hideLoading"
		});
	}
	
	function onRefreshClick() {
		setState({
			loadingIndicatorCss:"showLoading",
			data:state.data
		});
		fetData();
	}
	
	const columns = useMemo(
	  () => [
	        {
	          Header: "Destination",
	          accessor: "destination"
	        },
	        {
	          Header: "Direction",
	          accessor: "direction"
	        },
	        {
	          Header: "Last Update",
	          accessor: "lastUpdate"
	        },
	        {
	          Header: "Line",
	          accessor: "line"
	        },
	        {
	          Header: "Next Arrival",
	          accessor: "nextArrival"
	        },
	        {
	          Header: "Station",
	          accessor: "station"
	        },
	        {
	          Header: "Train Id",
	          accessor: "trainId"
	        },
	        {
	          Header: "Waiting(Seconds)",
	          accessor: "waitingSeconds"
	        },
	        {
	          Header: "Waiting Time",
	          accessor: "waitingTime"
	        }
	  ],
	  []
	);

	return (
			<div className="App">
				<div className="header"><span>MARTA Train Arrivals - All Stations</span></div>
					<Table columns={columns} data={state.data} refreshFnc={onRefreshClick} />
					<LoadingIndicator loadingCss={state.loadingIndicatorCss}/>
			</div>)
}

ReactDOM.render(<App />, document.getElementById('root'));

export default App;