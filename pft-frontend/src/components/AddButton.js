import useApi from "../Hooks/useApi";

function AddButton({ onClick, account, record, url }) {
   const { loading, error, request } = useApi();
    let result;
    
    const postAccount = async () => {
         result = await request("POST", url, account);
      if(result) {
        console.log("Account created successfully:", result);
        } else {
          alert("❌ Failed to add account");
        }
    }

    const postRecord =  async () => {
        result = await request("POST", url, record);
      if(result) {
        console.log("Record created successfully:", result);
        } else {
          alert("❌ Failed to add record");
        }
    }

    
    const handleClick = async () => {
   //This function will be used to create a new account
      
        if(account){
            postAccount();
        }
        if(record){
            postRecord();
        }
         if (onClick) onClick(result);
    
  
}
  return (
     <button className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700" onClick={handleClick} disabled={loading}>
            Save
    </button>
  );
}

export default AddButton;