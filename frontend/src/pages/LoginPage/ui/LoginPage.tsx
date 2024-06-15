import { Button, CssBaseline, Grid, Paper, Typography } from "@mui/material";
import {LoginByEmail} from "@/features/Login/LoginByEmail";
import {NoAuthComposite} from "@/shared/components/Layout/NoAuthComposite";
import {useLoginPage} from "@/pages/LoginPage/libs/useLoginPage";
import Link from "next/link";


export const LoginPage = () => {
  const { } = useLoginPage();
  return (
    <NoAuthComposite>
      <Typography component="h1" variant="h5" margin="normal" align={"center"}>
        Вход
      </Typography>

      <LoginByEmail />
      <Link href={"/registration"}>
        <Button variant={"text"} color={"primary"} size={"small"} >Регистрация</Button>
      </Link>

    </NoAuthComposite>
  )
};
