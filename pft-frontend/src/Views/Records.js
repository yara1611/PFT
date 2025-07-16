import SortingSideBar from '../components/SortingSideBar';  
import useApi from '../Hooks/useApi';
import { useEffect, useState } from 'react';


/*
Optimize:
we can change this and make it a list view that serves
both accounts and records and make accounts and records components 
*/

function Records(){
  // Fetching accounts data from the mock API    
  const { request } = useApi(); // Only need request
  const [records, setRecords] = useState([]); // ✅ Store accounts here
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);


useEffect(() => {
    const fetchRecords = async () => {
      setLoading(true);
      try {
        const result = await request("GET", "https://67ec96c2aa794fb3222e2a13.mockapi.io/Record");
        setRecords(result || []);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchRecords();
  }, []);

  //This adds a new account to the state without refetching
  const handleRecordAdded = (newRecord) => {
    setRecords((prev) => [...prev, newRecord]);
  };



//add the record src
    return <>
        <div className="container grid grid-cols-2 gap-4 h-screen w-full p-6">
        <div className="bg-white border border-gray-200 w-64 p-4 rounded-xl">
        <SortingSideBar 
        title='Records' 
        url='https://67ec96c2aa794fb3222e2a13.mockapi.io/Record'
        onAccountAdded={handleRecordAdded} />
      </div>
      <div id="accounts" className="p-4 w-full rounded-xl bg-white border border-gray-200">
        <ul>
            {
                records.map((rec,index)=>(
                   <div className="bg-zinc-200 mb-1 rounded-s p-2" key={index}>
                    <span className="font-bold">
                        {rec.name} 
                    </span>
                    <span className="float-right">
                        {rec.balance}$
                    </span>
                    
                    </div>
                )  
                )
                   
            }
        </ul>
      </div>
      
    </div>
    
  </>    
}


export default Records;