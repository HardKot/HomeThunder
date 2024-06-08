import { PropsWithChildren } from "react";
import themeApp from "@/app/themeApp";
import { AppRouterCacheProvider } from "@mui/material-nextjs/v13-appRouter";
import { ThemeProvider as MUIThemeProvider } from "@mui/material/styles";

export const ThemeProvider = ({ children }: PropsWithChildren) => (
  <AppRouterCacheProvider>
    <MUIThemeProvider theme={themeApp}>{children}</MUIThemeProvider>
  </AppRouterCacheProvider>
);
