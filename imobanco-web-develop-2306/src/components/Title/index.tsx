import { ReactNode } from "react";
import Favicon from "../../assets/favicon.png";

interface Props {
  children?: ReactNode;
}

const Title = ({ children }: Props) => {
  return (
    <div className="flex items-center">
      <div className="flex">
        <img src={Favicon} />
        <span className="w-1 bg-main rounded-lg ml-6" />
      </div>
      <h1 className="text-4xl font-medium uppercase ml-1.5">{children}</h1>
    </div>
  );
};

export default Title;
