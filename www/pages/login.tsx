import { LoginPage } from "@/pages/LoginPage";
import { serverSideTranslations } from "next-i18next/serverSideTranslations";

const Login = () => {
  return <LoginPage />;
};

export default Login;

export async function getServerSideProps({ locale }) {
  return {
    props: {
      ...(await serverSideTranslations(locale, ["common", "login"])),
    },
  };
}
