import { ReactNode } from "react";

interface Props {
  children?: ReactNode;
}

const Button = ({ children, ...rest }: Props) => {
  return (
    <>
      <button
        {...rest}
        className="bg-main p-2 mt-6 text-white uppercase text-2xl font-medium rounded-md hover:bg-gray-900 hover:duration-500 hover:ease-in"
      >
        {children}
      </button>
    </>
  );
};

export default Button;
