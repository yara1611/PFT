import SortingSideBar from "../components/SortingSideBar.js";

function Accounts() {
    const accounts =[{name:'Account 1', balance:10},{name:'Account 2', balance:10},{name:'Account 3', balance:10}];
    
  return (<>
    <div className="container grid grid-cols-2 gap-0 h-screen w-full p-6">
        <div className="bg-white border border-gray-200 w-64 p-4 rounded-xl">
        <SortingSideBar title='Accounts'/>
      </div>
      <div id="accounts" className="p-4 mr-0 w-full">
        <ul>
            {
                accounts.map((acc,index)=>(
                   <div className=" bg-white border border-gray-200 mb-1 rounded-xl p-2 flex justify-between " key={index}>
                    <span className="">
                        {acc.name} 
                    </span>
                    <span className="">
                        type
                    </span>
                    {/*type span*/}
                    <span className="">
                        {acc.balance}$
                    </span>
                    
                    </div>
                )  
                )
                   
            }
        </ul>
      </div>
      
    </div>
    
  </>
    
  );
}
export default Accounts;