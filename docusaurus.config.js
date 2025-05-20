
// @ts-check
import { themes as prismThemes } from 'prism-react-renderer';

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'Malloc Security SDK',
  tagline: 'Android Privacy & Threat Detection, Done Right',
  favicon: 'img/favicon.ico',
  url: "https://Malloc-Tech.github.io",
  baseUrl: '/sdk/',
  organizationName: "Malloc-Tech",
  projectName: 'sdk',
  onBrokenLinks: 'warn',
  onBrokenMarkdownLinks: 'warn',
  deploymentBranch: 'gh-pages',
  trailingSlash: false,
  i18n: {
    defaultLocale: 'en',
    locales: ['en'],
  },

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          path: 'docs',
          routeBasePath: '/',
          sidebarPath: require.resolve('./sidebars.js'),
          editUrl: 'https://github.com/Malloc-Tech/sdk/edit/main/',
        },
        blog: false, // ⛔ Turned off blogging since not used
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
  /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
      ({
        image: 'img/malloc-banner.jpg',
        navbar: {
          title: 'Malloc Security SDK',
          logo: {
            alt: 'Malloc Logo',
            src: 'img/logo.jpg',
          },
          items: [
            {
              type: 'docSidebar',
              sidebarId: 'mallocSidebar',
              position: 'left',
              label: 'Docs',
            },

            {
              href: 'https://github.com/Malloc-Tech/sdk/',
              label: 'GitHub',
              position: 'right',
            },
          ],
        },
        footer: {
          style: 'dark',
          links: [
            {
              title: 'Documentation',
              items: [
                {
                  label: 'Getting Started',
                  to: '/getting-started',
                },
                {
                  label: 'API Reference',
                  to: '/api-reference/scanDevice',
                },
              ],
            },
            {
              title: 'Community',
              items: [
                {
                  label: 'GitHub',
                  href: 'https://github.com/mallocprivacy',
                },
                {
                  label: 'Twitter',
                  href: 'https://x.com/mallocprivacy',
                },
              ],
            },
          ],
          copyright: `© ${new Date().getFullYear()} Malloc. All rights reserved.`,
        },
        prism: {
          theme: prismThemes.github,
          darkTheme: prismThemes.dracula,
        },
      }),
};

export default config;
