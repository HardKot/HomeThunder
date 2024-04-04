import {useForm} from "react-hook-form";
import {RegistrationForm, useRegistrationMutation} from "@/entities/user";
import {useTranslation} from "next-i18next";

export const useRegistration = () => {
  const formMethod = useForm<RegistrationForm>()
  const {t} = useTranslation()
  const [registration, { isLoading }] = useRegistrationMutation()

  const onSubmit = formMethod.handleSubmit(registration)

  return {
    onSubmit,
    t,
    isLoading,
    formMethod,
  }
}
