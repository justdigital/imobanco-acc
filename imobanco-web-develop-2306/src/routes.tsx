import { createBrowserRouter } from 'react-router-dom'

import Login from './pages/Login';
import ForgotPassword from './pages/ForgotPassword';
export const router = createBrowserRouter([
  {
    path: "/",
    element: <Login />,
  },
  {
    path: "forgot-password",
    element: <ForgotPassword />
  }
]);
