import { Avatar, Box, Button, Grid, Typography } from "@mui/material";
import { FormProvider } from "react-hook-form";
import { useLogin } from "../libs/useLogin";
import { LoginByEmail } from "@/features/Login/LoginByEmail";

export const Login = () => {
  const { t, onSubmit, formMethod } = useLogin();
  return (
    <Box
      sx={{
        my: 8,
        mx: 4,
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
      }}
    >
      <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}></Avatar>
      <Typography component="h1" variant="h5">
        {t("Вход")}
      </Typography>
      <Grid component={"form"} noValidate onSubmit={onSubmit}>
        <Grid container>
          <FormProvider {...formMethod}>
            <LoginByEmail />
          </FormProvider>
        </Grid>
        <Button
          type="submit"
          fullWidth
          variant="contained"
          sx={{ mt: 3, mb: 2 }}
        >
          {t("Войти")}
        </Button>
      </Grid>
    </Box>
  );
};
