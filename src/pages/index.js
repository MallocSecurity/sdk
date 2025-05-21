import clsx from 'clsx';
import Link from '@docusaurus/Link';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import Layout from '@theme/Layout';
import Heading from '@theme/Heading';
import styles from './index.module.css';

function HomepageHeader() {
    const { siteConfig } = useDocusaurusContext();
    return (
        <header className={clsx('hero', styles.heroBanner)}>
            <link rel="icon" href="https://malloc-tech.github.io/sdk/favicon.ico?v=2"/>
            <div className="container">
                <div className={styles.heroContent}>
                    <div className={styles.heroText}>
                        <Heading as="h1" className={clsx('hero__title', styles.heroTitle)}>
                            {siteConfig.title}
                        </Heading>
                        <p className={clsx('hero__subtitle', styles.heroSubtitle)}>
                            Malloc Security SDK is a powerful Android library that helps developers integrate
                            real-time mobile protection into their apps.
                        </p>

                        <div className={styles.buttons}>
                            <Link className={styles.heroButton} to="/sdk/intro">
                                Get Started
                            </Link>
                        </div>
                    </div>

                </div>
            </div>
        </header>
    );
}

const sdkFeatures = [
    {
        title: 'Suspicious URL Check',
        imgUrl: './img/1.svg',
        description: 'Detect phishing or malicious links in real time.',
    },
    {
        title: 'Root Detection & Device Scan',
        imgUrl: './img/2.svg',
        description: 'Identify rooted or compromised devices and check for spyware.',
    },
    {
        title: 'Spyware Apps Detection',
        imgUrl: './img/3.svg',
        description: 'Scan for spyware and suspicious applications.',
    },
    {
        title: 'Malware File Checker',
        imgUrl: './img/4.svg',
        description: 'Analyze APKs and files for malware signatures.',
    },

];

function SDKIntroSection() {
    return (
        <section className={styles.sdkIntro}>
            <div className="container">


                <div className={styles.featuresGrid}>
                    {sdkFeatures.map((feature, idx) => (
                        <div key={idx} className={styles.featureCard}>
                            <img src={feature.imgUrl} alt={feature.title} className={styles.featureIcon} />
                            <h3 className={styles.featureTitle}>{feature.title}</h3>
                            <p className={styles.featureDescription}>{feature.description}</p>
                        </div>
                    ))}
                </div>

            </div>
        </section>
    );
}

export default function Home() {
    const { siteConfig } = useDocusaurusContext();
    return (
        <Layout
            title={`${siteConfig.title}`}
            description="Privacy-first Android SDK for mobile app security.">
            <HomepageHeader />
            <main>
                <SDKIntroSection />
            </main>
        </Layout>
    );
}
