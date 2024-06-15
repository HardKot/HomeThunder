import { RegistrationByEmail } from "@/features/Registration/RegistrationByEmail";
import { NoAuthComposite } from "@/shared/components/Layout/NoAuthComposite";
import { Typography } from "@mui/material";

export const RegistraionPage = () => (
  <NoAuthComposite>
    <Typography variant="h5" margin="normal" align="center">
      Регистрация
    </Typography>
    <RegistrationByEmail />   
  </NoAuthComposite>
)
