import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

const FeatureList = [
    {
        title: 'Simple SDK Integration',
        imgUrl: 'https://www.svgrepo.com/show/354331/coding.svg',
        description: (
            <>
                Just a single line of code to initialize the Malloc SDK. No extra setup or tokens needed—designed to get you up and running in seconds.
            </>
        ),
    },
    {
        title: 'Privacy First',
        imgUrl: 'https://www.svgrepo.com/show/309535/privacy-shield.svg',
        description: (
            <>
                We process the majority of data directly on the device. We do not collect personal data, and we do not store any user information—ensuring maximum privacy by design.
            </>
        ),
    },
    {
        title: 'Offer Security to Your Users',
        imgUrl: 'https://www.svgrepo.com/show/303251/shield-security.svg',
        description: (
            <>
                Build security-first mobile experiences with:
                <ul style={{ textAlign: 'left', marginTop: '0.5rem' }}>
                    <li>🔗 Suspicious URL check</li>
                    <li>📱 Root Detection</li>
                    <li>🕵️ Spyware apps detection</li>
                    <li>🦠 Malware scanning for files & APKs</li>
                    <li>🔐 Minimal permissions required</li>
                    <li>⚡ Lightweight & battery-friendly</li>
                </ul>
            </>
        ),
    },
];

function Feature({ imgUrl, title, description }) {
    return (
        <div className={clsx('col col--4', styles.featureCard)}>
            <div className={styles.featureContent}>
                <img src={imgUrl} alt={title} className={styles.featureIcon} />
                <Heading as="h3" className={styles.featureTitle}>{title}</Heading>
                <div className={styles.featureDescription}>{description}</div>
            </div>
        </div>
    );
}

export default function HomepageFeatures() {
    return (
        <section className={styles.features}>
            <div className="container">
                <div className="row">
                    {FeatureList.map((props, idx) => (
                        <Feature key={idx} {...props} />
                    ))}
                </div>
            </div>
        </section>
    );
}
