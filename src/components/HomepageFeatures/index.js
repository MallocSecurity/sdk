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
                Malloc processes everything on-device. No data leaves the user’s phone, ensuring privacy and compliance by design.
            </>
        ),
    },
    {
        title: 'Protect Your App',
        imgUrl: 'https://www.svgrepo.com/show/303251/shield-security.svg',
        description: (
            <>
                Detect root access, emulator environments, VPNs, and network attacks in real-time to protect your app from threats.
            </>
        ),
    },
];

function Feature({imgUrl, title, description}) {
    return (
        <div className={clsx('col col--4')}>
            <div className="text--center">
                <img className={styles.featureSvg} src={imgUrl} alt={title} style={{ height: 120 }} />
            </div>
            <div className="text--center padding-horiz--md">
                <Heading as="h3">{title}</Heading>
                <p>{description}</p>
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
