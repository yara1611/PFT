import React from 'react';
import { postData} from '../Hooks/useFetch';

function SortingSideBar(props){
    const [data, setData] = React.useState({name: props.acc.name, balance: props.acc.balance, type: props.acc.type});
    let {title,url} = props;
    const handleClick = async () => {
    // This function will be used to create a new account
  postData(data,url)
  
}
    
    return <>
        <div className='container'>
        <h1 className='text-2xl font-bold text-left mt-5 ml-2'> {title}</h1>
        {/*this will be used with create account from API */}
        <button className="w-24 p-2 mt-2 mb-2 ml-2 bg-blue-500 text-white rounded hover:bg-blue-600" onClick={handleClick}>Add</button>
        <input type="text"  placeholder="Search..." className="w-fit p-2 mt-2 mb-2 ml-2 border border-gray-300 rounded" />
        {/* if records do filter if accounts do sorting */}
        </div>

        
    </>
}
export default SortingSideBar;