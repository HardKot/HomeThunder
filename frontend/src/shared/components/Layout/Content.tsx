import { useStyle } from "@/shared/react-utils/useStyle";
import { Paper } from "@mui/material";
import { PropsWithChildren } from "react";

interface ContentProps extends PropsWithChildren {}

export const Content = ({ children }: ContentProps) => {
  const style = useStyle((theme) => ({
    padding: `${theme.spacing}`,
  }));

  return <Paper style={style}>{children}</Paper>;
};
