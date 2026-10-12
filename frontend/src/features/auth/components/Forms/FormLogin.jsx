import { useState } from 'react'
import Input from '../Inputs/Input'
import ButtonSignIn from '../Buttons/ButtonSignIn'
import ButtonSignInWithGoogle from '../Buttons/ButtonSignInWithGoogle'
import LinkSignUp from '../Links/LinkSignUp'
import { LoginService } from '../../services/LoginService'


const FormLogin = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const handleSubmitLogin = async (event) => {
        event.preventDefault();

        const token = await LoginService(username, password);
        
    }



    return (
        <form id="formLogin" onSubmit={handleSubmitLogin} className='flex flex-col gap-4 w-full p-4 bg-white rounded-lg shadow-md'>
            <h1 className='font-satisfy text-4xl text-center text-pink-950'>Confecciones DyM</h1>
            <hr className='border-t border-gray-300 mb-3' />
            <h2 className='font-satisfy text-2xl text-center text-pink-900'>Ingresa tus credenciales</h2>
            <Input 
                id="txtUsername"
                type="text" 
                placeholder="Usuario" 
                value={username} 
                onChange={
                (event) => setUsername(event.target.value)
                } 
            />
            <Input 
                id="txtPassword"
                type="password" 
                placeholder="Contraseña" 
                value={password} 
                onChange={
                (event) => setPassword(event.target.value)
                } 
            />
            <a href="#" className='text-sm text-blue-500 hover:text-blue-700'>¿Olvidaste tu contraseña?</a>
            <ButtonSignIn />
            <ButtonSignInWithGoogle />
            <LinkSignUp />
        </form>
    )
}

export default FormLogin;