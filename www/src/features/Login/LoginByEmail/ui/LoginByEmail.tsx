import { Checkbox, FormControlLabel, TextField } from "@mui/material";
import { Controller } from "react-hook-form";
import { useLoginByEmail } from "@/features/Login/LoginByEmail/libs/useLoginByEmail";

export interface LoginProps {
  footer?: JSX.Element;
}

export const LoginByEmail = ({ footer }: LoginProps) => {
  const { t, control } = useLoginByEmail();

  return (
    <>
      <Controller
        name={"email"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
          <TextField
            {...field}
            margin="normal"
            required
            fullWidth
            id="email"
            label={t("Эл.почта")}
            autoComplete="email"
            autoFocus
          />
        )}
      />
      <Controller
        name={"password"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
          <TextField
            {...field}
            margin="normal"
            required
            fullWidth
            id="password"
            type={"password"}
            label={t("Пароль")}
            autoComplete="password"
            autoFocus
          />
        )}
      />
      <Controller
        name={"rememberMe"}
        control={control}
        rules={{ required: true }}
        render={({ field }) => (
          <FormControlLabel
            control={<Checkbox {...field} color="primary" />}
            label={t("Запомнить меня")}
          />
        )}
      />
    </>
  );
};
