import { InputHTMLAttributes } from "react"

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
  name: string;
  label: string;
}

const Input = ({ name, label, ...rest }: InputProps) => {
  return (
    <>
      <label className="font-medium text-lg my-2" htmlFor={name}>{label}:</label>
      <input className="h-11 p-3 border border-main rounded-md outline-none text-md text-main 
        hover:drop-shadow-sm hover:bg-input hover:ease-in hover:duration-300 hover:border-2 
        focus:border-2" id={name} {...rest} />
    </>
  )
}

export default Input
