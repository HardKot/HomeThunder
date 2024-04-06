import { useTranslation } from "next-i18next";
import { useForm } from "react-hook-form";
import { useLoginMutation } from "@/entities/user/api/userAPI";
import { UserLogin } from "@/entities/user/models/UserLogin";

export const useLogin = () => {
  const { t } = useTranslation();
  const formMethod = useForm<UserLogin>();
  const [login, {}] = useLoginMutation();

  const onSubmit = formMethod.handleSubmit(login);

  return {
    t,
    formMethod,
    onSubmit,
  };
};
