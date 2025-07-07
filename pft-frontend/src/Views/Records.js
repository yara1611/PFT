import React from 'react';
import SortingSideBar from '../components/SortingSideBar';  

function Records(){
    const records =[{name:'record 1', balance:10},{name:'record 2', balance:10},{name:'record 3', balance:10}];
    const acc1 ={
  name: 'new rec',
  balance: '100',
  type: 'savings'
}

//add the record src
    return <>
        <div className="container grid grid-cols-2 gap-4 h-screen w-full p-6">
        <div className="bg-white border border-gray-200 w-64 p-4 rounded-xl">
        <SortingSideBar title='Records' acc={acc1}/>
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