import "@/styles/globals.css";
import {appWithTranslation} from 'next-i18next'
import type {AppProps} from "next/app";

import {AppEntary} from "@/app/AppEntary";

export function App(props: AppProps) {
  return <AppEntary {...props} />;
}


export default appWithTranslation(App)

