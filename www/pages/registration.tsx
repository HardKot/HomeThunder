import { serverSideTranslations } from "next-i18next/serverSideTranslations";
import { RegistrationPage } from "@/pages/RegistrationPage";

const Registration = () => {
  return <RegistrationPage />;
};

export default Registration;

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
