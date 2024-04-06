import { useTranslation } from "next-i18next";

export const useLoginPage = () => {
  const { t } = useTranslation();

  return {
    t,
  };
};
