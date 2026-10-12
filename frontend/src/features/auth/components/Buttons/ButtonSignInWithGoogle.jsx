import GoogleIcon from '../../assets/GoogleIcon.png'

const ButtonSignInWithGoogle = () => {

    return (
        <button type="button" className="bg-olive-100 text-black w-full py-2 rounded-3xl border border-olive-400 cursor-pointer hover:bg-olive-200">
            <img src={GoogleIcon} alt="Google Icon" width="30" height="30" className='inline mx-1' /> Iniciar Sesión con Google
        </button>
    )
}

export default ButtonSignInWithGoogle;