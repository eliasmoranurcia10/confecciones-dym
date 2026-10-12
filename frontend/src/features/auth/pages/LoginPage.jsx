import FormLogin from "../components/Forms/FormLogin";
import EmblemaConfeccionesDyM from "../assets/EmblemaConfeccionesDyM.png"

const LoginPage = () => {

    return (
        <div className="flex flex-row items-center justify-around">
            <img src={EmblemaConfeccionesDyM} alt="Emblema Confecciones DyM" className="max-lg:hidden h-screen" />
            <div className="w-lg max-lg:w-md max-lg:mx-auto p-4">
                <FormLogin/>
            </div>
        </div>
        
    )
}

export default LoginPage;