import {DocumentContext, Head, Html, Main, NextScript} from "next/document";
import {documentGetInitialProps, DocumentHeadTags, DocumentHeadTagsProps} from "@mui/material-nextjs/v13-pagesRouter";

export default function Document(props: DocumentHeadTagsProps) {
  return (
    <Html lang="en">
      <Head>
        <DocumentHeadTags {...props} />
      </Head>
      <body>
        <Main />
        <NextScript />
      </body>
    </Html>
  );
}

export async function getInitialProps(ctx: DocumentContext)  {
  return await documentGetInitialProps(ctx, {});
}
