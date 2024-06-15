import { Avatar, Box, Button, Grid, Typography } from "@mui/material";
import { FormProvider } from "react-hook-form";
import { useLogin } from "../libs/useLogin";
import { LoginByEmail } from "@/features/Login/LoginByEmail";

export const Login = () => {
  const model = useLogin();
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
        Вход
      </Typography>
      <Grid component={"form"} noValidate>
          <Grid container>
            <LoginByEmail
                CSRFToken={model.csrf_token}
            />
          </Grid>

      </Grid>
    </Box>
  );
};
