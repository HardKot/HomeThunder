import { PropsWithChildren } from "react";
import themeApp from "@/app/themeApp";
import { ThemeProvider as MUIThemeProvider } from "@mui/material/styles";

export const ThemeProvider = ({ children }: PropsWithChildren) => (
  <MUIThemeProvider theme={themeApp}>{children}</MUIThemeProvider>
);
