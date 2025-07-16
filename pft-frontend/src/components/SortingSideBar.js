import {useState} from 'react';
import AddForm from "../components/AddForm.js";
import AddRecordForm from "../components/AddRecordForm.js";

//implement search, sorting and filtering

function SortingSideBar(props){

const [showModal, setShowModal] = useState(false);
const [isAccounts, setIsAccounts] = useState(props.title === 'Accounts');
const { title,url} = props;

    const handleClick = () => {
        setShowModal(true);
    }
    
    const handleOnClose = () => {
        setShowModal(false);
        window.location.reload();
    }
    return <>
        <div className='container'>
        <h1 className='text-2xl font-bold text-left mt-5 ml-2'> {title}</h1>
        {/*this will be used with create account from API */}
        <button className="w-fit p-2 mt-2 mb-2 ml-2 bg-blue-500 text-white rounded hover:bg-blue-600" onClick={handleClick}>New {title.slice(0,-1).toLowerCase()}</button>
        <input type="text"  placeholder="Search..." className="w-fit p-2 mt-2 mb-2 ml-2 border border-gray-300 rounded" />
        {/* if records do filter if accounts do sorting */}
        </div>

        {/* modal for adding new account */}
        {isAccounts && showModal && <AddForm  url={url} onClose={handleOnClose} />}
        {!isAccounts && showModal && <AddRecordForm  url={url} onClose={handleOnClose} />}

        
    </>
}
export default SortingSideBar;