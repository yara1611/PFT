import { useEffect, useState } from "react";

function useGet(url) {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect((()=>{
      console.log(`Fetching: ${url}`);

      if(!url) return;
      setLoading(true);
      fetch(url)
      .then((res)=>{
        if(!res.ok){
          throw new Error(`HTTP error! status: ${res.status}`);
        }
        
        return res.json()
      })
      .then((data)=>{
      
        setData(data);
        setError(null);
      })
      .catch((err)=>{
        setError(err);
        setData(null);
      })
      .finally(()=>{
        setLoading(false);
      })
    }),[url])
    return { data, loading, error };
}
export default useGet;