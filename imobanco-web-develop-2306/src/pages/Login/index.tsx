import Background from '../../components/Background'
import Button from '../../components/Button'
import Input from '../../components/Input'
import LinkTo from '../../components/Link'
import Title from '../../components/Title'

const Login = () => {
  return (
    <>
      <Background>
        <div className='flex flex-col w-full items-center'>
          <Title>Login</Title>
          <form className='flex flex-col mt-12 w-2/4' id="login-form">
            <Input name='username' label='Nome de usuário' type='text' />
            <Input name='password' label='Senha' type='password' />
            <div className='flex justify-end'>
             <LinkTo to='forgot-password'>Esqueceu sua senha?</LinkTo>
            </div>
            <Button>Entrar</Button>
            <div className='flex items-center justify-center mt-4'>
            <LinkTo to='register'>Não tem uma conta? Registre-se</LinkTo>
            </div>
          </form>
        </div>
      </Background>
    </>
  )
}

export default Login
