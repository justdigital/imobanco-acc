import { ReactNode } from "react";
import { Link } from "react-router-dom";

interface Props {
  to: string;
  children?: ReactNode;
}

const LinkTo = ({ to, children }: Props) => {
  return (
    <Link
      className="text-main mt-2 cursor-pointer font-medium hover:text-secondary hover:ease-in hover:duration-200"
      to={to}
    >
      {children}
    </Link>
  );
};

export default LinkTo;
