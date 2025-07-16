import AddButton from "./AddButton";
import { useState } from "react";
function AddForm({ onClose,url }) {

    const [name, setName] = useState('');
    const [balance, setBalance] = useState('');
    const [type, setType] = useState('savings');

    let newAccount = {
        name: name,
        balance: balance,
        type: type
    };

    return (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
            <div className="bg-white p-6 rounded-xl w-full max-w-md shadow-lg">
                <div className="flex justify-between items-center mb-4">
                    <h2 className="text-2xl font-bold">Add New Account</h2>
                    <button
                        onClick={onClose}
                        className="text-gray-500 hover:text-red-500 text-xl"
                    >
                        &times;
                    </button>
                </div>

                <form className="space-y-4">
                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Account Name:
                        </label>
                        <input
                            type="text"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring focus:ring-blue-300"
                            placeholder="Enter account name"
                            onChange={(e)=>{setName(e.target.value)}}
                        />
                    </div>

                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Balance:
                        </label>
                        <input
                            type="number"
                            className="w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring focus:ring-blue-300"
                            placeholder="Enter balance"
                            onChange={(e)=>{setBalance(e.target.value)}}
                        />
                    </div>
                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Account Type:
                        </label>
                        <select className="w-full px-3 py-2 border border-gray-300
                            rounded-md shadow-sm focus:outline-none focus:ring focus:ring-blue-300" onChange={(e)=>{setType(e.target.value)}}>
                            <option value="savings">Savings</option>
                            <option value="checking">Checking</option>
                            <option value="credit">Credit</option>
                        </select>
                    </div>
                </form>


                <div className="flex justify-end mt-6">
                    <button
                        className="px-4 py-2 bg-gray-300 hover:bg-gray-400 text-gray-700 rounded mr-2"
                        onClick={onClose}
                    >
                        Cancel
                    </button>
                    <AddButton onClick={onClose} account={newAccount} url={url}/>
                </div>
            </div>
        </div>
    );
}

export default AddForm;