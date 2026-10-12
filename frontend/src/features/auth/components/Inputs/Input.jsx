

const Input = ({ id, type, placeholder, value, onChange }) => {



    return (
        <>
            <div>
                <label htmlFor={id} className="block text-pink-900 font-medium mb-2 text-xl">{placeholder}</label>
                <input
                    id={id}
                    type={type}
                    placeholder={placeholder}
                    value={value}
                    onChange={onChange}
                    className="bg-white text-gray-700 py-1 w-full font-sans text-lg placeholder:text-gray-400 border border-gray-300 rounded-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            </div>
        </>
    );
}

export default Input;