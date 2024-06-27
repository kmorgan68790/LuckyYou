import React from 'react';
// import leo from '.././assets/leo.jpg';
const zodiacImages = {
    Aries: '/images/zodiac/aries.jpg',
    Taurus: '/images/zodiac/taurus.jpg',
    Gemini: '/images/zodiac/gemini.jpg',
    Cancer: '/images/zodiac/cancer.jpg',
    Leo: '/images/zodiac/leo.jpg',
    Virgo: '/images/zodiac/virgo.jpg',
    Libra: '/images/zodiac/libra.jpg',
    Scorpio: '/images/zodiac/scorpion.jpg',
    Sagittarius: '/images/zodiac/sagittarius.jpg',
    Capricorn: '/images/zodiac/capricorn.jpg',
    Aquarius: '/images/zodiac/aquarius.jpg',
    Pisces: '/images/zodiac/pisces.jpg'
};

const ZodiacItem = ({ zodiac }) => {
    const zodiacImage = zodiacImages[zodiac.zodiacName] || '.././assets/clover.jpg';

    return (
        <div class="d-flex flex-column justify-content-center align-items-center text-justify-center" >
            <h3 className="text-center">{zodiac.zodiacName}</h3>
            <img src={zodiacImage} alt={`${zodiac.zodiacName} icon`} width='600' height='500' className="mb-3"/>
            <p style={{ maxWidth: '150vh' }}>{zodiac.zodiacDescription}</p>
        </div>
    );
};

export default ZodiacItem;
