import SortingSideBar from "../components/SortingSideBar.js";

function Accounts() {
    const accounts =[{name:'account 1', balance:10},{name:'account 2', balance:10},{name:'account 3', balance:10}];
    
  return (<>
    <div className="container grid grid-cols-2 gap-4 h-screen w-screen bg-zinc-300 p-6">
        <div className="bg-blue-100 w-full p-4 rounded-xl">
        <SortingSideBar title='Accounts'/>
      </div>
      <div id="accounts" className="bg-red-100 p-4 w-full rounded-xl">
        <ul>
            {
                accounts.map((acc,index)=>(
                   <div className="bg-zinc-100 mb-1 rounded-s p-2" key={index}>
                    <span className="font-bold">
                        {acc.name} 
                    </span>
                    <span className="float-right">
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