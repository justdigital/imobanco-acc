import { ReactNode } from "react";

import Logo from "../../assets/logo.svg";

interface Props {
  children?: ReactNode;
}

const Background = ({ children }: Props) => {
  return (
    <div className="w-full h-screen flex">
      <div className="bg-main h-full w-1/3 flex justify-center px-4">
        <img className="w-60" src={Logo} alt="logo-imobanco" />
      </div>
      <div className="bg-white w-full h-full flex justify-center items-center">
        {children}
      </div>
    </div>
  );
};

export default Background;
