import React, {useState} from "react";
import { useTable, useFilters, usePagination } from "react-table";
import "./Table.css";

/**
 * Table component.
 * @param An array with 3 items: columns, data, and a callback function for reloading data.
 * @returns A div that contain an html table and a button for filter data
 */
function Table({ columns, data, refreshFnc }) {
	const {
		getTableProps,
		getTableBodyProps,
		headerGroups,
		rows,
		prepareRow,
		setFilter,
		useSortBy
	} = useTable({columns, data}, useFilters);
	
	//Create a state
	const [filterInput, setFilterInput] = useState("");

	// Update the state when input changes
	const handleFilterChange = e => {
		const value = e.target.value || undefined;
		setFilter("destination", value);
		setFilterInput(value);
	};
  
	return (
			<div id="train-data-view">
				<button onClick={refreshFnc}>Reload</button>
				<input value={filterInput} onChange={handleFilterChange} placeholder={"Search destination"}></input>
				<table {...getTableProps()}>
			    	<thead>
			        	{headerGroups.map(headerGroup => (
			        			<tr {...headerGroup.getHeaderGroupProps()}>
			        				{headerGroup.headers.map(column => (
			        						<th {...column.getHeaderProps()}>{column.render("Header")}</th>
			        				))}
		        				</tr>
			        	))}
		        	</thead>
		        	<tbody {...getTableBodyProps()}>
		        		{rows.map((row, i) => {
		        			prepareRow(row);
	        				return (
	        						<tr {...row.getRowProps()}>
	        							{row.cells.map(cell => {
	        								return <td {...cell.getCellProps()}>{cell.render("Cell")}</td>;
	        							})}
	        						</tr>
	        				);
		        		})}
	        		</tbody>
        		</table>
    		</div>
	);
}

export default Table;