import Link from 'next/link'

export default function Home() {
	return (
		<main>
			<h1>Welcome to Animal Adoption!</h1>
			<p>Rescue a pet today!</p>

			<Link href="/animals">Animals</Link>
		</main>
	)
}
