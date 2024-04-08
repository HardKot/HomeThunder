import { LoginPage } from "@/pages/LoginPage";
import { serverSideTranslations } from "next-i18next/serverSideTranslations";

const Login = () => {
  return <LoginPage />;
};

export default Login;

interface ServerSideProps {
  locale: string;
}

export async function getServerSideProps({ locale }: ServerSideProps) {
  return {
    props: {
      ...(await serverSideTranslations(locale, ["common", "login"])),
    },
  };
}
