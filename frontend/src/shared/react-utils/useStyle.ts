import {Theme, useTheme} from "@mui/system";
import React, {useMemo} from "react";

export const useStyle = (props: (theme: Theme) => {[key: string]: React.CSSProperties}) => {
  const theme = useTheme()

  return  useMemo(() => props(theme), [theme, props])
}
