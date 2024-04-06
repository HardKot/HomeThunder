import { serverSideTranslations } from "next-i18next/serverSideTranslations";
import { RegistrationPage } from "@/pages/RegistrationPage";

const Registration = () => {
  return <RegistrationPage />;
};

export default Registration;

export async function getServerSideProps({ locale }) {
  return {
    props: {
      ...(await serverSideTranslations(locale, ["common", "login"])),
    },
  };
}
