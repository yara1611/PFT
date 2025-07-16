import {useState } from "react";
import SortingSideBar from "../components/SortingSideBar.js";
import useApi from "../Hooks/useApi.js";
import { useEffect } from "react";

function Accounts() {
// Fetching accounts data from the mock API

  const { request } = useApi(); // Only need request
  const [accounts, setAccounts] = useState([]); // ✅ Store accounts here
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);


useEffect(() => {
    const fetchAccounts = async () => {
      setLoading(true);
      try {
        const result = await request("GET", "https://67ec96c2aa794fb3222e2a13.mockapi.io/Accounts");
        setAccounts(result || []);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchAccounts();
  }, []);

  // ✅ This adds a new account to the state without refetching
  const handleAccountAdded = (newAccount) => {
    setAccounts((prev) => [...prev, newAccount]);
    window.location.reload();
  };

  return (<>
    <div className="container grid grid-cols-[auto_1fr] gap-0 h-screen w-full p-6">
        <div className="bg-white border border-gray-200 w-64 p-4 rounded-xl">
        <SortingSideBar 
          title='Accounts' 
          url='https://67ec96c2aa794fb3222e2a13.mockapi.io/Accounts'
          onAccountAdded={handleAccountAdded} />
      </div>
      <div id="accounts" className="p-4 mr-0 w-full">
        {loading &&<svg class="bg-indigo-500 mr-3 size-5 animate-spin" viewBox="0 0 24 24"></svg>}

        {error && (
          <div className="text-red-500">Error: {error.message}</div>
        )}
        {!loading && !error && (
          <ul id="accounts-list">
            {accounts?.map((acc) => (
              <div
                className="bg-white border border-gray-200 mb-1 rounded-xl p-2 flex items-center gap-2"
                key={acc.id}
              >
                <span className="flex-1">{acc.name}</span>
                <span className="flex-1 text-center">{acc.type}</span>
                <span className="flex-1 text-right">{acc.balance}$</span>
              </div>
            ))}
          </ul>
        )}
      </div>
      
    </div>
    
  </>
    
  );
}
export default Accounts;