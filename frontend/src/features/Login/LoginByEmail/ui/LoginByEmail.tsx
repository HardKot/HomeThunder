"use client"
import {Button} from "@mui/material";
import { useLoginByEmail } from "@/features/Login/LoginByEmail/libs/useLoginByEmail";
import {CheckboxElement, FormContainer, TextFieldElement} from "react-hook-form-mui";
import {Controller} from "react-hook-form";
import loginByEmail from "@/features/Login/LoginByEmail/api/loginByEmail";
import Box from "@mui/material/Box";
import { FormUtils } from "@/shared/libs/FormUtils";
import Lodash from "lodash";
import CsrfToken from "@/shared/components/FormInput/CsrfToken";

export interface LoginProps {
}

export const LoginByEmail = ({ }: LoginProps) => {

  return (
    <FormContainer
      FormProps={{
      }}
      onSuccess={Lodash.flow(FormUtils.fromObject, loginByEmail)}
    >
      <TextFieldElement
        name={"email"}
        rules={{ required: true }}
        label={"Эл.почта"}
        autoComplete="email"
        fullWidth
        margin="normal"
      />
      <TextFieldElement
        name={"password"}
        rules={{ required: true }}
        type={"password"}
        label={"Пароль"}
        autoComplete="password"
        fullWidth
        margin="normal"
      />
      <Box className={"flex justify-between items-center"}>
        <CheckboxElement
          name={"rememberMe"}
          label={"Запомнить меня"}
        />
        <CsrfToken />
        <Button variant={"text"}>Забыли пароль?</Button>
      </Box>
      <Button type={"submit"} variant={"contained"} fullWidth>Войти</Button>
    </FormContainer>
  );
};
