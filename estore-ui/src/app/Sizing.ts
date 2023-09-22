export enum Sizing {
	MENS = 'Mens',
	WOMENS = 'Womens',
	KIDS = 'Kids'
}

export function toString(sizing: Sizing): string {
	switch (sizing) {
		case Sizing.MENS:
			return 'Men\'s';
		case Sizing.WOMENS:
			return 'Women\'s';
		case Sizing.KIDS:
			return 'Kids';
		default:
			return '';
	}
}
